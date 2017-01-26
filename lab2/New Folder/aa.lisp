;(if (> 4 5)                               
;    (print "4 falsely greater than 5!") 
;  (print "4 is not greater than 5!"))   

(setq a 10)
(cond ((> a 20)
   (format t "~% a is less than 20"))
   (t (format t "~% value of a is ~d " a)))
