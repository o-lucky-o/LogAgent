package rbt.log.agent;


import org.springframework.web.bind.annotation.*;
import rbt.log.agent.dto.ErrorLog;

import java.util.List;

/**
 * ErrorLog Module
 *
 * @author c50033056
 * @since 2023-08-29
 */

@RestController
//@Api(tags = "Error Log Module")
@RequestMapping("/error_log")
public class ErrorLogController {

    /**
     * 获取errorLog
     */
    @RequestMapping(method = RequestMethod.POST)
    public List<ErrorLog> getErrorLogFrom(@RequestBody List<ErrorLog> errorLogList) {

        System.out.println(errorLogList);

        return errorLogList;
    }

}