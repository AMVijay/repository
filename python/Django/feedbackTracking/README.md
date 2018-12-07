# Project Feedback Tracking Application

This UI based application to track feedback from team on Project.

## Technical Stack
* Python 3.7
* Django
* SQLite

## Design


## Development Setup Instructions.
1. Run `envSetup.cmd` - Set Python Environment Variable in Windows
2. Create Virtual Environment using command `python -m venv feedbackTrackEnv`
3. Activate Virtual Environment by run activate command in venv `venv\Scripts\activate`
4. Upgrade PIP `python -m pip install --upgrade pip`
5. Install Django `pip install Django`
6. To check Django Version `python -m django --version`
7. To create new Web Project/Application use command `django-admin startproject webroot`
8. Run the created Application `python manage.py runserver 8080`
9. To create sub application, use command `python manage.py startapp submitFeedback`


## Reference for Developer Material
* https://simpleisbetterthancomplex.com/article/2017/08/19/how-to-render-django-form-manually.html