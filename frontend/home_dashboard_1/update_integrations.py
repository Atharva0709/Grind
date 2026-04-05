import re
fp = r'd:\PROJECTS\java_project\frontend\integrations_hub_1\code.html'
with open(fp, 'r', encoding='utf-8') as f:
    html = f.read()

html = html.replace('??', '').replace('?', 'ALERT:')
x = html.find('<script>')
html = html[:x] + """<script>
    document.addEventListener('DOMContentLoaded', () => {
        const userId = localStorage.getItem('userId');
        if (!userId) window.location.href = '../index.html';
        const username = localStorage.getItem('username');
        if (username) {
            document.querySelectorAll('*').forEach(el => {
                if (el.childNodes.length === 1 && el.childNodes[0].nodeType === 3 && el.innerText.includes('STUDENT_01')) {
                    el.innerText = el.innerText.replace('STUDENT_01', username.toUpperCase());
                }
            });
        }
        document.querySelectorAll('span').forEach(s => {
            if(s.innerText.includes('account_circle') && s.parentElement) {
                let p = s.parentElement; p.style.cursor = 'pointer'; p.title = 'Click to Logout';
                p.onclick = () => { localStorage.clear(); window.location.href = '../index.html'; };
            }
            if(s.innerText.includes('OCT 24, 2024')) {
                s.innerText = new Date().toLocaleDateString('en-US', { month: 'short', day: 'numeric', year: 'numeric' }).toUpperCase();
            }
        });
        const syncBtn = document.querySelector('button.bg-[#FFE135]');
        if (syncBtn && syncBtn.innerText.includes('SYNC')) {
            syncBtn.onclick = function() {
                this.innerHTML = '<span class=\"material-symbols-outlined\">sync</span> SYNCING...';
                setTimeout(() => { 
                    this.innerHTML = '<span class=\"material-symbols-outlined\">check</span> SYNCED'; 
                    setTimeout(() => { this.innerHTML = '<span class=\"material-symbols-outlined\">sync</span> SYNC ALL'; }, 2000); 
                }, 1000);
            };
        }
        document.querySelectorAll('button').forEach(btn => {
            let txt = btn.innerText || '';
            const isClickableIcon = Array.from(btn.children).some(c => c.innerHTML.includes('delete'));
            if (txt.includes('Archive') || txt.includes('Mute') || txt.includes('Resolve') || isClickableIcon) {
                btn.onclick = (e) => {
                    const card = e.target.closest('.bg-[#FFFBF0]');
                    if (card && confirm('Remove this item?')) card.style.display = 'none';
                };
            } else if (txt.includes('Reply') || txt.includes('Open') || txt.includes('View Logs')) {
                btn.onclick = () => alert('Action triggered successfully.');
            } else if (txt.includes('Create Folder')) {
                btn.onclick = () => { const name = prompt('Folder name:'); if (name && name.trim()) alert('Folder created.'); };
            }
        });
    });
</script></body></html>"""
with open(fp, 'w', encoding='utf-8') as f:
    f.write(html)
