from django.db import models

class FeedbackTopic(models.Model):
    class Meta:
        db_table = 'topics'
    id = models.AutoField(primary_key=True)
    topic = models.TextField(max_length=255,unique=True)
    createDt = models.TextField(max_length=10)
    createdBy = models.TextField(max_length=255)
