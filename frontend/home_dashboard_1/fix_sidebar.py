import os, glob, re

home_path = r'd:\\PROJECTS\\java_project\\frontend\\home_dashboard_1\\code.html'
with open(home_path, 'r', encoding='utf-8') as f:
    home_html = f.read()

aside_match = re.search(r'<aside.*?</aside>', home_html, re.DOTALL)
if aside_match:
    aside_html = aside_match.group(0)
    
    # Strip active classes
    t_aside = re.sub(r'border-yellow-400 bg-stone-900 text-yellow-400(.*?)translate-x-1', 
                     r'border-transparent text-white\g<1>hover:bg-stone-900 hover:border-yellow-400/50 hover:text-yellow-200', aside_html, flags=re.DOTALL)
    
    print('Aside template extracted.')
    print('Writing to all pages...')
    
    folders = [f for f in os.listdir(r'd:\\PROJECTS\\java_project\\frontend') if os.path.isdir(os.path.join(r'd:\\PROJECTS\\java_project\\frontend', f))]
    for folder in folders:
        fp = os.path.join(r'd:\\PROJECTS\\java_project\\frontend', folder, 'code.html')
        if os.path.exists(fp):
            with open(fp, 'r', encoding='utf-8') as f:
                content = f.read()
            
            new_aside = t_aside
            
            # Make the relevant link active
            folder_prefix = folder.split('_')[0]
            link_pattern = r'(<a class=\")([^\"]*)(\".*?href=\"\.\./' + folder_prefix + r'[^/]+/code\.html\")'
            
            def replace_active(m):
                cls = m.group(2)
                cls = cls.replace('border-transparent', 'border-yellow-400 bg-stone-900')
                cls = cls.replace('text-white', 'text-yellow-400')
                cls = cls.replace('hover:bg-stone-900', '')
                cls = cls.replace('hover:border-yellow-400/50', '')
                cls = cls.replace('hover:text-yellow-200', '')
                return m.group(1) + cls.strip() + ' translate-x-1' + m.group(3)
                
            new_aside = re.sub(link_pattern, replace_active, new_aside)
            content = re.sub(r'<aside.*?</aside>', new_aside, content, flags=re.DOTALL)
            
            with open(fp, 'w', encoding='utf-8') as f:
                f.write(content)
            print(f'Updated side nav in {folder}')
