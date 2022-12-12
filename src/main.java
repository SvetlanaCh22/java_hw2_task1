// Дана строка sql-запроса "select * from students where ".
// Сформируйте часть WHERE этого запроса, используя StringBuilder.
// Данные для фильтрации приведены ниже в виде json строки.
// Если значение null, то параметр не должен попадать в запрос.
// Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}

// Чубченко Светлана

import java.util.Map;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class main {
    public static void main(String[] args) {
        // задаем строку json
        String json = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":null}";
        ObjectMapper mapper = new ObjectMapper();
        // парсим json
        try {
            // создаем список из двух полей из json
            Map<String, String> params = mapper.readValue(json, Map.class);
            // выводим  SQL запрос
            System.out.println("select * from students where " + getQuery(params));
        } catch (IOException e) {
            // если ошибка парсинга
            e.printStackTrace();
        }
    }

    public static String getQuery(Map<String, String> params)
    {
        StringBuilder s = new StringBuilder();
        // переьбираем список имя значение
        for (Map.Entry<String,String> pair : params.entrySet())
        {
            // если параметр не null и не пустой
            if ((pair.getValue() != null) && (!pair.getValue().equals("")))
            {
                s.append(pair.getKey() +" = '" + pair.getValue()+"' and ");
            }
        }
        // удаляем лишний and в конце запроса
        s.delete(s.toString().length()-5,s.toString().length());
        return s.toString();
    }

}