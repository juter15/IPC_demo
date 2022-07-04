package com.example.ipc_demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class IpcController {
    private final IpcService ipcService;
    @GetMapping("/")
    public ResponseEntity ipcRequest(@RequestParam("TID") String tId, @RequestParam("STAT") String stat){
        return ResponseEntity.ok(ipcService.RequestSet(tId, stat));

    }
}
