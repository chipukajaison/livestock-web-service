package zw.co.lws.livestockwebservice.api.timetable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.lws.livestockwebservice.commons.Response;
import zw.co.lws.livestockwebservice.domain.TaskStatus;
import zw.co.lws.livestockwebservice.domain.TimeTable;
import zw.co.lws.livestockwebservice.service.timetable.TimeTableRequest;
import zw.co.lws.livestockwebservice.service.timetable.TimeTableResponse;
import zw.co.lws.livestockwebservice.service.timetable.TimeTableService;
import zw.co.lws.livestockwebservice.service.timetable.TimeTableUpdateRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/time-table")
@Tag(name = "Time-Table Controller", description = "Time-Table endpoints")
public class TimeTableController {
    private final TimeTableService timeTableService;

    public TimeTableController(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<TimeTableDto> create(@RequestBody TimeTableRequest timeTableRequest){
        TimeTableResponse timeTableResponse = timeTableService.create(timeTableRequest);
        TimeTableDto timeTableDto = TimeTableDto.of(timeTableResponse.getTimeTable());
        return ResponseEntity.ok(timeTableDto);
    }

    @GetMapping("/get-by/{taskStatus}")
    public ResponseEntity<List<TimeTableDto>> findAllByStatus(@PathVariable TaskStatus taskStatus){
        List<TimeTableResponse> timeTableResponseList = timeTableService.findAllByTaskStatus(taskStatus);
        List<TimeTableDto> timeTableDtoList = new ArrayList<>();
        timeTableResponseList.stream().forEach(timeTableResponse -> timeTableDtoList.add(new TimeTableDto(timeTableResponse.getTimeTable())));
        return ResponseEntity.ok(timeTableDtoList);
    }

    @GetMapping("/get-by-date")
    public ResponseEntity<List<TimeTableDto>> findAllByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("date") LocalDate date){
        List<TimeTableResponse> timeTableResponseList = timeTableService.findByDate(date);
        List<TimeTableDto> timeTableDtoList = new ArrayList<>();
        timeTableResponseList.stream().forEach(timeTableResponse -> timeTableDtoList.add(new TimeTableDto(timeTableResponse.getTimeTable())));
        return ResponseEntity.ok(timeTableDtoList);
    }
    @PutMapping("/update")
    public ResponseEntity<TimeTableDto> update(@RequestBody TimeTableUpdateRequest timeTableUpdateRequest){
        TimeTableResponse timeTableResponse = timeTableService.update(timeTableUpdateRequest);
        TimeTableDto timeTableDto = TimeTableDto.of(timeTableResponse.getTimeTable());
        return ResponseEntity.ok(timeTableDto);
    }

}
