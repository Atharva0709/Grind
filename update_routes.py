import os, re

base = r'D:/PROJECTS/java_project/frontend'
files = [os.path.join(r, f) for r, d, files in os.walk(base) for f in files if f == 'code.html']

routes = {
    'HOME': '../home_dashboard_1/code.html',
    'TASKS &amp; STREAK': '../tasks_streak_1/code.html',
    'TASKS & STREAK': '../tasks_streak_1/code.html',
    'TIME AWARENESS': '../time_awareness_1/code.html',
    'SKILLS &amp; GOALS': '../skills_goals_1/code.html',
    'SKILLS & GOALS': '../skills_goals_1/code.html',
    'INTEGRATIONS': '../integrations_hub_1/code.html',
    'PRODUCTIVITY': '../productivity_tools_1/code.html',
    'WHITEBOARD': '../whiteboard_canvas_1/code.html',
    'SETTINGS': '../settings_1/code.html'
}

for path in files:
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()

    def replace_href(match):
        full_a = match.group(0)
        inner_text = re.sub(r'<[^>]+>', '', full_a).strip()
        for name, href in routes.items():
            if inner_text.endswith(name) or name in inner_text:
                return re.sub(r'href=[\"\'][^\"\']*[\"\']', f'href="{href}"', full_a)
        return full_a

    content = re.sub(r'<a\b[^>]*>[\s\S]*?</a>', replace_href, content)

    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)


