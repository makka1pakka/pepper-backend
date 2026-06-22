package com.cz.controller;

import com.cz.service.SleepService;
import com.cz.vo.ResultVo;
import com.cz.vo.SleepVo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sleep")
public class SleepController {

    @Autowired
    private SleepService sleepService;

    @GetMapping("/all")
    public ResultVo getSleeps() {
        return sleepService.getAllSleeps();
    }

    @GetMapping("/{id}")
    public ResultVo getSleepById(@PathVariable Integer id) {
        return sleepService.getSleepById(id);
    }
    public static class DataReport {
        @JsonProperty("payload")
        private SleepVo payload;

        public SleepVo getPayload() {
            return payload;
        }
    }

    @PostMapping("/create")
    public ResultVo createSleep(@RequestBody DataReport dataReport) {
        SleepVo sleepVo = dataReport.getPayload();
        return sleepService.createSleep(sleepVo);
    }

    @PutMapping("/update/{id}")
    public ResultVo updateSleep(@PathVariable Integer id, @RequestBody SleepVo sleepVo) {
        return sleepService.updateSleep(id, sleepVo);
    }

    @DeleteMapping("/delete/{id}")
    public ResultVo deleteSleep(@PathVariable Integer id) {
        return sleepService.deleteSleep(id);
    }

    @GetMapping("/recent/{userId}")
    public ResultVo getRecentSleep(@PathVariable String userId) {
        return sleepService.getRecentSleep(userId);
    }
}