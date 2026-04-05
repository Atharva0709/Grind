import os, glob, re

target_dir = r"D:\PROJECTS\java_project\frontend"
home_path = os.path.join(target_dir, "home_dashboard_1", "code.html")
with open(home_path, 'r', encoding='utf-8') as f:
    home_html = f.read()

aside_match = re.search(r'<aside.*?</aside>', home_html, re.DOTALL)
if aside_match:
    aside_html = aside_match.group(0)
    t_aside = re.sub(r'border-yellow-400 bg-stone-900 text-yellow-400(.*?)translate-x-1', 
                     r'border-transparent text-white\g<1>hover:bg-stone-900 hover:border-yellow-400/50 hover:text-yellow-200', aside_html, flags=re.DOTALL)
    
    print('Writing consistent asides to all pages...')
    
    folders = [f for f in os.listdir(target_dir) if os.path.isdir(os.path.join(target_dir, f))]
    for folder in folders:
        fp = os.path.join(target_dir, folder, 'code.html')
        if os.path.exists(fp):
            with open(fp, 'r', encoding='utf-8') as f:
                content = f.read()
            
            new_aside = t_aside
            folder_prefix = folder.split('_')[0]
            
            # Make active section
            def repl(m):
                s = m.group(2)
                s = s.replace('border-transparent', 'border-yellow-400 bg-stone-900')
                s = s.replace('text-white', 'text-yellow-400')
                s = s.replace('hover:bg-stone-900', '')
                s = s.replace('hover:border-yellow-400/50', '')
                s = s.replace('hover:text-yellow-200', '')
                return m.group(1) + s.strip() + " translate-x-1" + m.group(3)
                
            pat = r'(<a class=")([^"]*)(".*?href="\.\./' + folder_prefix + r'[^/]+/code\.html")'
            new_aside = re.sub(pat, repl, new_aside)
            
            content = re.sub(r'<aside.*?</aside>', new_aside, content, flags=re.DOTALL)
            
            with open(fp, 'w', encoding='utf-8') as f:
                f.write(content)
            print("Updated " + folder)
