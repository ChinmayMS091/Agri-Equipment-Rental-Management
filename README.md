# Agri Equipment Rental Management System

![Home Page of the WebSite](Project.png)

Marketplace for renting farm equipment. Users browse and book, vendors list and manage, admins keep it all running.

Quick Overview
--------------

This is built with **Java 8, Spring Boot 2.7.x, Thymeleaf, MySQL, and Maven**. Standard web app setup - controllers handle requests, Thymeleaf renders pages, JPA talks to the database.

Who Does What
--------------

**Users:** Sign up → browse equipment → book what you need → pay → done. Forgot password? We'll send an OTP.

**Vendors:** Register → add equipment with photos → manage bookings → track rentals → make money.

**Admins:** Control users, vendors, equipment, bookings from a dashboard.

Getting Started
---------------

**Step 1:** Clone and cd into the folder

**Step 2:** Create the database:
```sql
CREATE DATABASE agri CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

**Step 3:** Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/agri
spring.datasource.username=root
spring.datasource.password=your_password
server.port=9095
```

**Step 4:** Run it:
```bash
mvnw.cmd spring-boot:run       # Windows
./mvnw spring-boot:run         # Mac/Linux
```

Open `http://localhost:9095/` and you're done!

The Database
------------

Six main tables: **newuser** (users), **NewVendor** (vendors), **AddEquipment** (equipment), **BookEqp** (bookings), **MBookEqp** (advanced bookings), **AdminUser** (admins).

Hibernate creates tables automatically - no manual SQL needed.

Main Features
-------------

- User signup/login with OTP password recovery
- Equipment listing with photos and GPS location
- Booking system with status tracking
- Email notifications for bookings
- File uploads (max 15MB)
- Vendor & admin dashboards

Email Setup
-----------

For password recovery and notifications, use Gmail:

1. Enable 2-step verification on your Google account
2. Generate an App Password: https://myaccount.google.com/apppasswords
3. Add to `application.properties`:
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

File Uploads
------------

Equipment photos and documents (supports jpg, png, gif, pdf):
```properties
uploadDir=/resources/static/
spring.servlet.multipart.max-file-size=15MB
```

Key URLs
--------

- `/` - Home
- `/userlogin` - User login
- `/vendorlogin` - Vendor login
- `/alogin` - Admin login
- `/bookeqp` - Browse equipment
- `/add_equipment` - Add equipment
- `/displaymybooking` - Your bookings

Troubleshooting
---------------

**MySQL connection fails?** Check if MySQL is running, verify credentials, ensure `agri` database exists.

**Emails not sending?** Verify SMTP settings. If using Gmail, use an app-specific password (not your regular password). Check port 587 isn't blocked.

**Port 9095 in use?** Change it in settings: `server.port=8080`

**Template errors?** Make sure HTML files are in `src/main/resources/templates/`

Security Notes
--------------

Currently uses basic session-based auth. For production: use Spring Security, enable HTTPS, hash passwords properly, don't store credentials in code - use environment variables.

Main Features You'll Find
-------------------------

**User Accounts:**
- Sign up with email verification
- Login safely with sessions
- Manage your profile
- Forget your password? Just answer an OTP

**Equipment Management:**
- Add equipment with photos and descriptions
- Edit or delete listings
- Set locations with GPS coordinates
- Track what's available

**Booking:**
- Browse equipment
- Make a booking request
- Track your bookings
- Pay for rentals
- Cancel if you need to

**Email Notifications:**
- Get emails when you sign up
- Notifications for booking updates
- Password recovery emails
- Everything goes through SMTP

**File Uploads:**
- Upload equipment photos
- Max file size is 15MB
- Images stored in static folder
- JPG, PNG, PDF all supported

Available Endpoints
-------------------

Here are the main URLs you can hit:

**User stuff:**
- `/` - Home page
- `/userlogin` - Login form
- `/userregister` - Sign up
- `/usermenu` - Dashboard after login
- `/uforget` - Forgot password
- `/displaymybooking` - Your bookings

**Vendor stuff:**
- `/vendorlogin` - Vendor login
- `/vendorregister` - Become a vendor
- `/vendormenu` - Vendor dashboard
- `/add_equipment` - Add new equipment
- `/updateeqp/{id}` - Edit equipment

**Equipment and booking:**
- `/bookeqp` - Browse equipment
- `/eqp_vendor_list` - Equipment by vendor
- `/booking` - Make a booking
- `/pay` - Payment page

**Admin stuff:**
- `/alogin` - Admin login
- `/adminmenu` - Admin dashboard
- `/view_user` - All users
- `/view_vendor` - All vendors


Security (Important to Know)
----------------------------

**Right now:**
- Users log in with username and password
- Sessions managed using HTTP sessions
- Roles separated (user, vendor, admin)
- Password recovery uses OTP

**For production:**
- Use Spring Security instead of basic auth
- Add password hashing (bcrypt)  
- Enable HTTPS
- Don't store credentials in code

Want to Help?
-------------

Fork → create a branch → make changes → commit → push → open PR.

Keep commits focused, follow existing code style.

License
-------

No specific license yet. Contact the project owner if you want to use it.

Future Ideas
------------

Spring Security upgrade, REST API, payment gateways, booking calendar, user ratings, equipment recommendations, real-time chat, mobile app.

Have Issues?
-----------

Open an issue on the repository. We're responsive and welcome feedback.

---

Built for farmers. Making equipment rental simple.
