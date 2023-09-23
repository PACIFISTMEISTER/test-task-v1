## **Тестовое задание  https://docs.google.com/document/d/1G_bNiKfEWXorOOry79s7UzPTNvYy4tRaYqnUnhPxuiU/edit**

ENDPOINTS:

/api/v1/data POST

на вход подается следующий набор данных:

Обязательный заголовок:

        "TOKEN":""

Тело:

        {
            "data" : ""
        }

результат:

        {
                "status": "success",
                "errors": [],
                "result": {
                    "h": 25,
                    "a": 1,
                    "ф": 1
                }
        }    

Status статус операции (success, error),

errors массив ошибок

result объект с данными

Возможные ошибки и коды ошибок

        При несовпадении то заданного в приложении и токена в заголовке код - 401, текст - invalid token TOKEN
        При ошибке на стороне сервера код - 500, текст - server side error
        При ошибке валидации код 400, текст - сообщение о невалидности данных

## **СТАРТ**

Билд докера

    docker build -t task .

Запуск контейнера

    docker run -e access_token=hah -e server_port=8889 -p 8889:8889 task

В таком случае обращаться нужно по порту 8889 и в каждом запросе должен быть заголовок TOKEN со значением hah