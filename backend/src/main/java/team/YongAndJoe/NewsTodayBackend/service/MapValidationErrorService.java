package team.YongAndJoe.NewsTodayBackend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import team.YongAndJoe.NewsTodayBackend.util.AjaxResponseBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationErrorService {
    public ResponseEntity<?> mapValidationService(BindingResult result){

        if(result.hasErrors()){
            AjaxResponseBody body;
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            body = AjaxResponseBody.FAIL("Validation Error", errorMap);
            return ResponseEntity.badRequest().body(body);
        }

        return null;
    }
}
