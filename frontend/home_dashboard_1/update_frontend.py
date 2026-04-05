import os
root_dir = r"d:\PROJECTS\java_project\frontend"
updates = 0
for root, dirs, files in os.walk(root_dir):
    for f in files:
        if f.endswith(".html"):
            fp = os.path.join(root, f)
            with open(fp, "r", encoding="utf-8") as file:
                content = file.read()
            original = content
            content = content.replace("GRIND OS", "SANKALP")
            content = content.replace("'http://localhost:8082/api/tasks'", "`http://localhost:8082/api/tasks?userId=${userId}`")
            # also replace any bare API_URL that might exist
            content = content.replace("API_URL = 'http://localhost:8082/api/tasks';", "API_URL = `http://localhost:8082/api/tasks?userId=${userId}`;")
            if content != original:
                with open(fp, "w", encoding="utf-8") as file:
                    file.write(content)
                updates += 1
                print(f"Updated: {fp}")
print(f"Done renaming. Total files updated: {updates}")
