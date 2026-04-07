# GRIND - Production Deployment Guide

## System Overview
GRIND is a high-output productivity engine built with:
- **Frontend**: Vanilla JavaScript, Tailwind CSS, Material Icons
- **Backend**: Spring Boot 4.0.5, MySQL
- **Architecture**: Microservices-ready REST API with JWT authentication

## Deployment Checklist

### Pre-Deployment
- [ ] Database: MySQL GRIND DB initialized and running
- [ ] Backend: Spring Boot configured on port 8082
- [ ] Frontend: Served from `/frontend` directory on port 8000
- [ ] Environment variables: API_URL set to production backend URL

### Running the Application

#### Backend
```bash
cd GRIND_backend
./mvnw clean spring-boot:run
# Runs on http://localhost:8082
```

#### Frontend  
```bash
cd frontend
python -m http.server 8000
# Serves on http://localhost:8000
```

#### Access
- Login: http://localhost:8000
- Auto-redirects to home_dashboard_1/code.html after authentication

## Core Pages

| Page | URL | Feature |
|------|-----|---------|
| Login | `/index.html` | User authentication & registration |
| Home | `/home_dashboard_1/code.html` | Dashboard with Pomodoro & stats |
| Tasks | `/tasks_streak_1/code.html` | Task management with CRUD operations |
| Timeline | `/time_awareness_1/code.html` | Time-based goals with NOW marker |
| Skills | `/skills_goals_1/code.html` | Skill tracking with Power Stats radar |
| Integrations | `/integrations_hub_1/code.html` | Calendar & email integration |
| Productivity | `/productivity_tools_1/code.html` | Pomodoro, Weekly Audit, Skill Log |
| Whiteboard | `/whiteboard_canvas_1/code.html` | Drawing canvas with persistence |
| Settings | `/settings_1/code.html` | User preferences & data export |

## API Endpoints

### Authentication
- `POST /api/users/login` - Login
- `POST /api/users/register` - Register

### Users
- `GET /api/users/{id}` - Get user profile
- `PUT /api/users/{id}` - Update user
- `GET /api/users/{id}/streak` - Get streak count
- `POST /api/users/{id}/streak` - Update streak

### Tasks
- `GET /api/tasks?userId={id}` - List user tasks
- `POST /api/tasks` - Create task
- `PUT /api/tasks/{id}` - Update task
- `DELETE /api/tasks/{id}` - Delete task

### Skills
- `GET /api/skills/{userId}` - List user skills
- `POST /api/skills` - Create skill
- `PUT /api/skills/{userId}` - Update skill proficiency

## Data Persistence

### LocalStorage Keys
- `userId` - Current logged-in user ID
- `username` - Current logged-in username
- `timelineGoals` - Custom time-based goals
- `canvasDrawing` - Whiteboard canvas drawing (PNG)
- `fontsize` - User font size preference
- `accentColor` - UI accent color preference

### Database Tables
- `users` - User profiles with streak and discipline scores
- `task` - User tasks with status tracking
- `skill` - User skills with proficiency percentage

## Key Features

### Whiteboard Canvas
- Draw with Pencil (2px) and Marker (5px tools
- Create Shapes: Rectangle, Circle, Arrow, Diamond, Star
- Add Text and Images
- Undo/Redo with history
- Save to localStorage (PNG)
- Export as PNG file

### Timeline Management
- Add custom time-based goals (HH:MM format)
- Real-time NOW marker updates every second
- Visual timeline from 6 AM to 11 PM

### Power Stats Radar Chart
- Dynamic SVG polygon visualization
- Updates based on skill proficiency
- Connected to real API skill data

### Dynamic Settings
- User profile management
- Appearance customization
- Notification preferences
- Font size adjustment
- Data export functionality

## Performance Optimizations

### Canvas Rendering
- RequestAnimationFrame for 60fps drawing
- Cached button DOM references
- Reduced DOM queries via event delegation

### Network
- API endpoints caching with localStorage
- JSON serialization optimization (quality: 0.9)
- Minimal external dependencies

### UI
- Toast notifications (non-blocking)
- No modal alerts or confirms (except critical)
- CSS Tailwind for optimized styling

## Removed Features (For Production)

### Duplicate Pages
- Removed: home_dashboard_2, settings_2, etc. (kept _1 versions only)
- Removed: tasks_streak_2, tasks_streak_3 (kept _1 only)
- Removed: grind_neo_brutal/ design folder

### External Images
- Demo images replaced with API-sourced data
- Insensible placeholder images removed
- Kept minimal branding assets only

### Debug Elements
- Removed all emoji from logs and alerts
- Cleaned console output
- Production error handling implemented

## Configuration

### Frontend Environment
Edit API_URL in page scripts if needed:
```javascript
const API_URL = 'http://localhost:8082/api';
```

### Backend Configuration
Check `application.properties` for:
- Database connection string
- JWT token settings
- CORS configuration

## Security Notes

## Running Production

### Docker (Recommended)
```bash
docker-compose up -d
```

### Manual
1. Start MySQL: `mysql.exe -u root -p`
2. Create database: `CREATE DATABASE GRIND_db;`
3. Run backend: `./mvnw spring-boot:run`
4. Run frontend: `python -m http.serve 8000`
5. Access: Navigate to http://localhost:8000

## Monitoring

### Backend Logs
- Check Spring Boot console for errors
- Database connection status
- API response times

### Frontend Logs
- Open DevTools Console (F12)
- Check for JavaScript errors
- Verify API calls in Network tab

## Troubleshooting

### Whiteboard Not Saving
- Check localStorage available space
- Confirm browser allows localStorage
- Verify canvas is fully rendered

### API Connection Failed
- Ensure backend running on port 8082
- Check CORS headers in Spring Boot
- Verify database connection

### Skills/Tasks Not Loading
- Verify user ID in localStorage
- Check API returns data
- Inspect Network tab for 404 errors

## Future Enhancements

- Multi-user collaboration (cursor tracking, real-time sync)
- Cloud storage for whiteboard drawings
- Mobile app version
- Advanced analytics dashboard
- Machine learning for task prioritization
- Voice command integration
- Offline mode support

## Support & Maintenance

For issues or improvements:
1. Check logs for error details
2. Verify database schema
3. Test API endpoints with curl/Postman
4. Clear localStorage if cache issues occur

---

**Version**: 1.0.0-PRODUCTION  
**Last Updated**: April 2026  
**Status**: Ready for Deployment
