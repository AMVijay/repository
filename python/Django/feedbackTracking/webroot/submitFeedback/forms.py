from django import forms

class NewFeedbackForm(forms.Form):
    topicName = forms.CharField(max_length=255,min_length=3)

    # def __init__(self, *args, **kwargs):
    #     self.request = kwargs.pop("request")
    #     super(NewFeedbackForm, self).__init__(*args, **kwargs)