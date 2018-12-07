from django.urls import path
from . import views

urlpatterns = [
    path('',views.init, name='start'),
    path('add',views.add, name='add'),
]
