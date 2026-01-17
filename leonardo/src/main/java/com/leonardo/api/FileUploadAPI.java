package com.leonardo.api;

import com.leonardo.service.IFileStorageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileUploadAPI {
  private final IFileStorageService storageService;

  @PostMapping(
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      path = "/upload")
  public ResponseEntity<String> uploadFile(@RequestParam("file") List<MultipartFile> files) {
    String message = "";
    try {
      for (MultipartFile file : files) {
        storageService.save(file);
      }
      message = "Uploaded the file successfully: ";
      return ResponseEntity.status(HttpStatus.OK).body(message);
    } catch (Exception e) {
      message = "Could not upload the file: " + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }
  }

  //	  @GetMapping("/files")
  //	  public ResponseEntity<List<FileInfo>> getListFiles() {
  //	    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
  //	      String filename = path.getFileName().toString();
  //	      String url = MvcUriComponentsBuilder
  //	          .fromMethodName(FilesController.class, "getFile",
  // path.getFileName().toString()).build().toString();
  //
  //	      return new FileInfo(filename, url);
  //	    }).collect(Collectors.toList());
  //
  //	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  //	  }

  @GetMapping("/files/{filename:.+}")
  @ResponseBody
  public ResponseEntity<String> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok().body("/" + file.getFilename());
  }
}
