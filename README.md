REST service on SpringBoot
===
____

general description
-
It is necessary to write an automation of a resource for exchanging DVD discs - a set of REST services for a web application. Have a collection
discs for each participant. Disks can be taken and given away. Developed services should provide an opportunity for
accounting for disks from owners, transferring disks to other users, availability of free disks.

Detailed requirements
-
There are three entities in the system:

* Disk (DVD disc)<br>
*User<br>
* TakenItem (User-Disk bundle)

The application has five screens:
==

* authorization,
* list of own disks for each user,
* list of free disks (unused for all users),
* list of disks taken by the user;
* list of disks taken from the user (with an indication of who took it).

The disk can be taken and given away (without monetary payments), i.e. in the list of disks taken there should be a button "give", and in the list of disks
free "take" button.<br>
You need to design and implement REST services for such an application.<br>
It is necessary to use SpringBoot, JPA, as a DBMS - you can choose an embedded one (hsql). Sources - on github, bitbucket - on
selection, assembly - Maven.<br>

Recommendations
==
Try to ensure that your code is covered by unit tests<br>
It is recommended to provide logging of operations performed in the application (the log can be output to the console or saved to a file).<br>
The data received by the service may contain errors that must be reasonably handled: the client must receive
information about why his request was denied.<br>

Bonus
==
An additional bonus will be the ability to automatically generate an OpenAPI specification for the developed service
Больше информации об этом исходном тексте
Чтобы получить дополнительную информацию, введите исходный текст
Отправить отзыв
Боковые панели
