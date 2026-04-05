import os, re

base = r'D:/PROJECTS/java_project/frontend'
files = [os.path.join(r, f) for r, d, files in os.walk(base) for f in files if f == 'code.html']

auth_script = """
<script>
    // Global Auth Guard & Data Plugin
    document.addEventListener('DOMContentLoaded', () => {
        const userId = localStorage.getItem('userId');
        if (!userId) {
            window.location.href = '../index.html';
            return;
        }
        
        const username = localStorage.getItem('username');
        if (username) {
            // Update username in sidebars replacing STUDENT_01
            document.querySelectorAll('*').forEach(el => {
                if (el.childNodes.length === 1 && el.childNodes[0].nodeType === 3) {
                    if (el.innerText.includes('STUDENT_01')) {
                        el.innerText = el.innerText.replace('STUDENT_01', username);
                    }
                }
            });
        }

        // Make sidebars clickable for logout where account_circle resides
        document.querySelectorAll('span').forEach(s => {
            if(s.innerText.includes('account_circle')) {
                let p = s.parentElement;
                if(p) {
                    p.style.cursor = 'pointer';
                    p.title = 'Click to Logout';
                    p.onclick = function() {
                        localStorage.clear();
                        window.location.href = '../index.html';
                    };
                }
            }
        });
    });
</script>
"""

for path in files:
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()

    # Only inject if not already present
    if 'Global Auth Guard' not in content:
        content = content.replace('</body>', auth_script + '\n</body>')

        with open(path, 'w', encoding='utf-8') as f:
            f.write(content)
