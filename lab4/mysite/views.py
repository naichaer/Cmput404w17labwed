from django.http import HttpResponse
from django.shortcuts import render

def index(request):
	return HttpResponse("You are at the polls index.")
