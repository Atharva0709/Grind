# Email Configuration Guide for GRIND Password Reset Feature

## Overview
The GRIND system now includes email-based authentication with a "Forgot Password" feature that sends genuine reset password emails.

## Email Setup Instructions

### Using Gmail (Recommended)

#### Step 1: Enable 2-Factor Authentication
1. Go to [Google Account Security](https://myaccount.google.com/security)
2. Enable 2-Step Verification

#### Step 2: Generate App Password
1. Go to [Google Account App Passwords](https://myaccount.google.com/apppasswords)
2. Select "Mail" and "Windows Computer"
3. Google will generate a 16-character password
4. Copy this password

#### Step 3: Update Configuration
Edit sankalp_backend/src/main/resources/application.properties:

`properties
spring.mail.username=your-email@gmail.com
spring.mail.password=your-16-char-app-password
`

**Example:**
`properties
spring.mail.username=chinmay.soni@gmail.com
spring.mail.password=abcd efgh ijkl mnop
`

### Alternative: Using Environment Variables (Recommended for Production)

Set environment variables before running the backend:

**Windows PowerShell:**
`powershell
\="your-email@gmail.com"
\="your-app-password"
`

**Windows CMD:**
`cmd
set MAIL_USERNAME=your-email@gmail.com
set MAIL_PASSWORD=your-app-password
`

**Linux/Mac:**
`ash
export MAIL_USERNAME="your-email@gmail.com"
export MAIL_PASSWORD="your-app-password"
`

Then the application.properties will automatically pick them up:
`properties
spring.mail.username=\
spring.mail.password=\
`

## Testing the Password Reset Feature

### 1. Start the Backend
`ash
cd sankalp_backend
./mvnw clean spring-boot:run
`

### 2. Start the Frontend
`ash
cd frontend
python -m http.server 8000
`

### 3. Test Registration
1. Go to http://localhost:8000
2. Click "REGISTER NEW ID"
3. Enter your test email and password
4. You should receive a welcome email

### 4. Test Forgot Password
1. On the login page, click "FORGOT PASSWORD?"
2. Enter the email you registered with
3. You'll receive a password reset email with a link
4. Click the link (or copy the reset token)
5. Set a new password
6. Login with your new password

## API Endpoints

### Login
**POST** /api/users/login
`json
{
  "email": "user@example.com",
  "password": "password123"
}
`

### Register
**POST** /api/users/register
`json
{
  "email": "user@example.com",
  "password": "password123"
}
`

### Forgot Password
**POST** /api/users/forgot-password
`json
{
  "email": "user@example.com"
}
`

### Reset Password
**POST** /api/users/reset-password
`json
{
  "token": "uuid-token-from-email",
  "password": "newpassword123"
}
`

## Email Templates

### Welcome Email
Sent when a user registers. Contains:
- Welcome message
- Confirmation that account was created
- Instructions to login

### Password Reset Email
Sent when user clicks "Forgot Password". Contains:
- Reset link with token
- Reset token value
- Expiration time (1 hour)
- Security notice

## Security Notes

1. **Reset Token Expiry**: 1 hour (3600000 milliseconds)
2. **App Passwords**: Use Gmail App Passwords instead of your main password
3. **HTTPS**: In production, ensure all connections use HTTPS
4. **Token Storage**: Tokens are stored in the database with expiry times
5. **Password Hashing**: Consider implementing bcrypt for password hashing in production

## Troubleshooting

### "Failed to send email" Error
- Check email configuration in application.properties
- Verify Gmail app password is correct
- Ensure "Less secure apps" is enabled (if not using app password)
- Check internet connection

### Email Not Received
- Check spam/junk folder
- Verify email address is correct
- Check that SMTP server (smtp.gmail.com:587) is accessible
- Check firewall settings

### Reset Link Not Working
- Ensure token in URL matches exactly
- Check that link was not truncated
- Verify token hasn't expired (1 hour limit)
- Check backend is running on localhost:8082

## Configuration Files Modified

1. **application.properties** - Email SMTP settings
2. **pom.xml** - Added spring-boot-starter-mail dependency
3. **User.java** - Added resetToken and resetTokenExpiry fields
4. **UserController.java** - Added forgot-password and reset-password endpoints
5. **EmailService.java** - Created email sending service
6. **frontend/index.html** - Updated with email login and forgot password UI
7. **frontend/reset-password.html** - New page for password reset

## Next Steps for Production

1. Implement password hashing (bcrypt)
2. Add email verification for new registrations
3. Add rate limiting to prevent abuse
4. Implement JWT tokens for session management
5. Add email templates using Thymeleaf or FreeMarker
6. Set up email logging and monitoring
7. Add HTTPS/SSL certificates
8. Implement two-factor authentication (2FA)

---

**Setup Date**: April 7, 2026
**Status**: Ready for Testing
