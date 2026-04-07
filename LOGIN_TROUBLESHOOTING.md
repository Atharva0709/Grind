# GRIND Login & Registration Troubleshooting Guide

## Current Status
? Backend is running on http://localhost:8082
? Database connection is working
? User table exists with sample data

## How to Test Login/Registration

### 1. Open Developer Console
- Press **F12** in your browser
- Go to the **Console** tab
- You'll see detailed logs for every action

### 2. Test Registration
**Steps:**
1. Open http://localhost:8000 (or wherever your frontend is hosted)
2. Enter email: 	est@example.com
3. Enter password: password123
4. Click  REGISTER NEW ID

**Expected Output in Console:**
`
Registration attempt - Email: test@example.com
Sending registration to: http://localhost:8082/api/users/register
Response status: 200
Response text: {message:Registration successful. Please check your email.}
SUCCESS: Registration successful! You can now login.
`

### 3. Test Login
**Steps:**
1. Enter the same email and password you registered
2. Click BOOT SYSTEM

**Expected Output in Console:**
`
Login attempt - Email: test@example.com
Sending login to: http://localhost:8082/api/users/login
Response status: 200
Login response: {message: 'Login successful', id: 2, email: 'test@example.com'}
Stored - userId: 2
SUCCESS: Login successful! Redirecting...
`

## Common Issues & Solutions

### Issue 1: Connection error. Is the backend running?
**Cause:** Backend is not accessible at http://localhost:8082

**Solutions:**
1. Make sure the backend is running:
   `ash
   cd sankalp_backend
   mvn spring-boot:run
   `
2. Check if port 8082 is occupied:
   `powershell
   netstat -ano | findstr :8082
   `
3. If occupied, change the port in sankalp_backend\src\main\resources\application.properties:
   `
   server.port=8083
   `
   Then update API_URL in rontend/index.html to http://localhost:8083/api/users

### Issue 2: Invalid email or password
**Cause:** User doesn't exist or credentials are wrong

**Solutions:**
1. Make sure you registered first
2. Use the exact same email and password
3. Check the database:
   `sql
   SELECT * FROM users;
   `

### Issue 3: LocalStorage not saving
**Cause:** Browser LocalStorage is disabled or cleared

**Solutions:**
1. Check browser console for localStorage errors
2. Make sure cookies/storage are enabled
3. Clear cache and try again

### Issue 4: Redirect not working after login
**Cause:** Dashboard file path is incorrect

**Solutions:**
1. Make sure home_dashboard_1/code.html exists
2. Update the redirect path in the login script if needed:
   `javascript
   window.location.href = './home_dashboard_1/code.html';
   `

## Database Inspection

To check if users are being created:
`sql
-- Connect to MySQL
mysql -u root -p

-- Select the database
USE GRIND;

-- View all users
SELECT id, email, password FROM users;

-- Delete a test user if needed
DELETE FROM users WHERE email = 'test@example.com';
`

## Email Configuration (Optional)

If you want password reset emails to work:
1. Get a Gmail app password
2. Update sankalp_backend/src/main/resources/application.properties:
   `
   spring.mail.username=your-email@gmail.com
   spring.mail.password=your-app-password
   `

## Debug Mode: Step-by-Step

1. Open browser DevTools (F12)
2. Go to Console tab
3. Register or login
4. Watch the console output in real-time
5. If error occurs, look at the response status and message
6. Check the Network tab to see the actual HTTP request/response

## Key Files
- Frontend: rontend/index.html
- Backend: sankalp_backend/src/main/java/com/example/demo/UserController.java
- Database config: sankalp_backend/src/main/resources/application.properties
- User model: sankalp_backend/src/main/java/com/example/demo/User.java
# GRIND Login & Registration Troubleshooting Guide

## Current Status
? Backend is running on http://localhost:8082
? Database connection is working
? User table exists with sample data

## How to Test Login/Registration

### 1. Open Developer Console
- Press **F12** in your browser
- Go to the **Console** tab
- You'll see detailed logs for every action

### 2. Test Registration
**Steps:**
1. Open http://localhost:8000 (or wherever your frontend is hosted)
2. Enter email: 	est@example.com
3. Enter password: password123
4. Click  REGISTER NEW ID

**Expected Output in Console:**
`
Registration attempt - Email: test@example.com
Sending registration to: http://localhost:8082/api/users/register
Response status: 200
Response text: {message:Registration successful. Please check your email.}
SUCCESS: Registration successful! You can now login.
`

### 3. Test Login
**Steps:**
1. Enter the same email and password you registered
2. Click BOOT SYSTEM

**Expected Output in Console:**
`
Login attempt - Email: test@example.com
Sending login to: http://localhost:8082/api/users/login
Response status: 200
Login response: {message: 'Login successful', id: 2, email: 'test@example.com'}
Stored - userId: 2
SUCCESS: Login successful! Redirecting...
`

## Common Issues & Solutions

### Issue 1: Connection error. Is the backend running?
**Cause:** Backend is not accessible at http://localhost:8082

**Solutions:**
1. Make sure the backend is running:
   `ash
   cd sankalp_backend
   mvn spring-boot:run
   `
2. Check if port 8082 is occupied:
   `powershell
   netstat -ano | findstr :8082
   `
3. If occupied, change the port in sankalp_backend\src\main\resources\application.properties:
   `
   server.port=8083
   `
   Then update API_URL in rontend/index.html to http://localhost:8083/api/users

### Issue 2: Invalid email or password
**Cause:** User doesn't exist or credentials are wrong

**Solutions:**
1. Make sure you registered first
2. Use the exact same email and password
3. Check the database:
   `sql
   SELECT * FROM users;
   `

### Issue 3: LocalStorage not saving
**Cause:** Browser LocalStorage is disabled or cleared

**Solutions:**
1. Check browser console for localStorage errors
2. Make sure cookies/storage are enabled
3. Clear cache and try again

### Issue 4: Redirect not working after login
**Cause:** Dashboard file path is incorrect

**Solutions:**
1. Make sure home_dashboard_1/code.html exists
2. Update the redirect path in the login script if needed:
   `javascript
   window.location.href = './home_dashboard_1/code.html';
   `

## Database Inspection

To check if users are being created:
`sql
-- Connect to MySQL
mysql -u root -p

-- Select the database
USE GRIND;

-- View all users
SELECT id, email, password FROM users;

-- Delete a test user if needed
DELETE FROM users WHERE email = 'test@example.com';
`

## Email Configuration (Optional)

If you want password reset emails to work:
1. Get a Gmail app password
2. Update sankalp_backend/src/main/resources/application.properties:
   `
   spring.mail.username=your-email@gmail.com
   spring.mail.password=your-app-password
   `

## Debug Mode: Step-by-Step

1. Open browser DevTools (F12)
2. Go to Console tab
3. Register or login
4. Watch the console output in real-time
5. If error occurs, look at the response status and message
6. Check the Network tab to see the actual HTTP request/response

## Key Files
- Frontend: rontend/index.html
- Backend: sankalp_backend/src/main/java/com/example/demo/UserController.java
- Database config: sankalp_backend/src/main/resources/application.properties
- User model: sankalp_backend/src/main/java/com/example/demo/User.java
