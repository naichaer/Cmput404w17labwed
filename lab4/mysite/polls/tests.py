from django.test import TestCase
from .models import Question, Choice
import datetime
from django.utils import timezone
# Create your tests here.

class QuestionMethodTests(TestCase):

	def test_was_published_recently(self):
		time = timezone.now()+ datetime.timedelta(days=30)
		q= Question(pub_date=time)
		self.assertEqual(q.was_published_recently(),False)
