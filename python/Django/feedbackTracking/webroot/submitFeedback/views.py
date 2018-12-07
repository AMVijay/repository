from django.shortcuts import render,redirect
from django.http import HttpResponse
from .models import FeedbackTopic
from .forms import NewFeedbackForm

def index(request):
    return HttpResponse("Hello, World")


def init(request):
    topics_list = FeedbackTopic.objects.order_by('id')
    form = NewFeedbackForm()
    context = {'topics_list': topics_list,'form':form}
    return render(request,'submitFeedback.html', context)


def add(request):
    if request.method == 'POST':
        form = NewFeedbackForm(request.POST)
        if form.is_valid():
            model = FeedbackTopic(topic=form.cleaned_data.get('topicName'),createDt='2018/07/29',createdBy='test')
            model.save()

    return redirect('start')
