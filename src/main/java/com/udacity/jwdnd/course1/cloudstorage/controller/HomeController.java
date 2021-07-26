package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/home")
public class HomeController {

    private UserService userService;
    private NoteService noteService;
    private CredentialService credentialService;
    private FileService fileService;
    private String activeTab;
    private String fileuploadresult = null;


    public HomeController(FileService fileService, UserService userService, NoteService noteService, CredentialService credentialService){
        this.fileService = fileService;
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        activeTab = "files";
    }
    @GetMapping
    public String getHomePage(Authentication authentication, Model model){
        Integer userid = userService.getUserId(authentication.getName());
        model.addAttribute("notes", noteService.getAllNotesByUserId(userid));
        model.addAttribute("credentials", credentialService.getAllCredentialsByUserId(userid));
        model.addAttribute("files", fileService.getAllFilesByUserId(userid));
        model.addAttribute("activeTab", activeTab);
        model.addAttribute("fileuploadresult", fileuploadresult);
        return "home";
    }

    @PostMapping("/note")
    public String createNote(Authentication authentication, Note note){
        note.setUserid(userService.getUserId(authentication.getName()));
        if (note.getNoteId() != null){
            noteService.updateNote(note);
        } else {
            noteService.createNote(note);
        }
        activeTab = "notes";
        return "redirect:/home";
    }

    @DeleteMapping("/note/{noteid}")
    public String deleteNote(@PathVariable Integer noteid){
        noteService.deleteNote(noteid);
        activeTab = "notes";
        return "redirect:/home";
    }

    @PostMapping("/credential")
    public String createCredential(Authentication authentication, Credential credential) throws NoSuchAlgorithmException {
        credential.setUserid(userService.getUserId(authentication.getName()));
        if (credential.getCredentialId() != null){
            credentialService.updateCredential(credential);
        } else {
            credentialService.createCredential(credential);
        }
        activeTab = "credentials";
        return "redirect:/home";
    }

    @DeleteMapping("/credential/{credentialid}")
    public String deleteCredential(@PathVariable Integer credentialid){
        credentialService.deleteCredential(credentialid);
        activeTab = "credentials";
        return "redirect:/home";
    }

    @PostMapping("/file")
    public String createFile(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication) throws IOException {
        if (multipartFile.isEmpty()) {
            fileuploadresult = "Please select a file to upload.";
            return "redirect:/home";
        }

        Integer userid = userService.getUserId(authentication.getName());

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (fileService.isFileNameAvailable(fileName, userid)==false){
            fileuploadresult= "File with this name already exists. Please, choose file with different name.";
            return "redirect:/home";
        }

        File file = new File(null,fileName,
                multipartFile.getContentType(),
                String.valueOf(multipartFile.getSize()),
                userid,
                multipartFile.getBytes());

        if (fileService.createFile(file) <= 0){
            fileuploadresult = "File upload failed, please try again later";
        };
        activeTab = "files";
        return "redirect:/home";
    }
    @GetMapping("/file/{fileid}")
    public ResponseEntity downloadFile(@PathVariable Integer fileid, Model model) {
        File file = fileService.getFile(fileid);
        activeTab = "files";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContenttype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(new ByteArrayResource(file.getFiledata()));
    }
    @GetMapping("/file")
    public String listFile(@PathVariable Integer fileid, Model model) {
        model.addAttribute("files", fileService.getAllFilesByUserId(fileid));
        activeTab = "files";
        return "redirect:/home";
    }

    @DeleteMapping("/file/{fileid}")
    public String deleteFile(@PathVariable Integer fileid){
        fileService.deleteFile(fileid);
        activeTab = "files";
        return "redirect:/home";
    }

}
