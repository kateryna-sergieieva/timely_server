package timely_data.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import timely_data.data_model.Measure;
import timely_data.data_model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import timely_data.repository.SessionRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Kateryna Sergieieva on 06.07.2017.
 */

@RestController
public class TimelyDataController {

    @Autowired
    SessionRepository sessionRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveData(@RequestBody String timelyDataJsonStr){

        try {
            Set<Session> sessions = parseJson(timelyDataJsonStr);

            for (Session session: sessions) {

                sessionRepository.save(session);

            }

        }
        catch(JSONException jsonEx){
            return "JSON parse error.";
        }
        return "Done";
    }

    private Set<Session> parseJson(String timelyDataJsonStr) throws JSONException
    {
        JSONObject timelyDataJson = new JSONObject(timelyDataJsonStr);
        JSONArray sessionsJsonArr = timelyDataJson.getJSONArray("sessions");

        Set<Session> sessions = new HashSet<Session>();
        for (int i = 0; i < sessionsJsonArr.length(); i++)
        {
            JSONArray measuresJsonArr = sessionsJsonArr.getJSONObject(i).getJSONArray("measures");
            Date currentDate = new Date();
            sessionsJsonArr.getJSONObject(i).remove("measures");
            Session session = new Session(currentDate,  sessionsJsonArr.getJSONObject(i).toString());
            Set<Measure> measures = new HashSet<Measure>();

            for (int j = 0; j < measuresJsonArr.length(); j++)
            {
                Measure measure = new Measure(measuresJsonArr.getJSONObject(j).toString(), session);
                measures.add(measure);
            }
            session.setMeasures(measures);
            sessions.add(session);
        }
        return sessions;
    }
}
