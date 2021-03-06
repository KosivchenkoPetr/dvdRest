REST-сервис на SpringBoot
===
____

Общее описание
-
Необходимо написать автоматизацию ресурса по обмену DVD дисками - набор REST-сервисов для веб-приложения. Есть коллекция
дисков у каждого участника. Диски можно брать и отдавать. Разработанные сервисы должны предоставлять возможность для
учета дисков у собственников, передачи дисков другим пользователям, наличия свободных дисков.

Детальные требования
-
В системе три сущности:

* Disk (DVD-диск)<br>
* User<br>
* TakenItem (связка User-Disk)

В приложении предполагается пять экранов:
==

* авторизация,
* список собственных дисков у каждого пользователя,
* список свободных дисков (у всех пользователей невзятые),
* список дисков, взятых пользователем;
* список дисков, взятых у пользователя (с указанием, кто взял).

Диск можно взять и отдать (без денежных расчётов), т.е. в списке дисков взятых должна быть кнопка "отдать", а в списке дисков
свободных кнопка "взять".<br>
Необходимо спроектировать и реализовать REST-сервисы для такого приложения.<br>
Необходимо использовать SpringBoot, JPA, в качестве СУБД - на выбор, можно встраиваемую (hsql). Исходники – на github, bitbucket – на
выбор, сборка - Maven.<br>

Рекомендации
==
Постарайтесь обеспечить покрытие написанного кода юнит-тестами<br>
Рекомендуется обеспечить логгирование выполняемых в приложении операций (лог можно выводить в консоль или сохранять в файл).<br>
Получаемые сервисом данные могут содержать ошибки, которые должны быть разумно обработаны: клиент должен получить
информацию о том, почему его запрос был отклонен.<br>

Бонус
==
Дополнительным бонусом будет реализация возможности автоматической генерации OpenAPI-спецификации к разработанному сервису








