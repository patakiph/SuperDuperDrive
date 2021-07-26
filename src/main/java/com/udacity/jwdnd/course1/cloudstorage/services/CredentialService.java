package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class CredentialService {
    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;

    public CredentialService (CredentialsMapper mapper, EncryptionService encryptionService){
        this.credentialsMapper = mapper;
        this.encryptionService = encryptionService;
    }

    public Integer createCredential(Credential credential) throws NoSuchAlgorithmException {
        String key = encryptionService.getKey();
        String encrypted_password = encryptionService.encryptValue(credential.getPassword(), key);
        return credentialsMapper.insertCredential(new Credential(null,credential.getUrl(),credential.getUsername(), key, encrypted_password, credential.getUserid()));
    }

    public List<Credential> getAllCredentialsByUserId(Integer userid){
        List<Credential> credentials = credentialsMapper.getAllUserCredentials(userid);
        for (Credential credential: credentials){
            credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        }
        return credentials;
    }

    public void deleteCredential(Integer credentialId){
        credentialsMapper.deleteCredential(credentialId);
    }

    public Integer updateCredential(Credential credential) throws NoSuchAlgorithmException {
        String key = encryptionService.getKey();
        String encrypted_password = encryptionService.encryptValue(credential.getPassword(), key);
        credential.setPassword(encrypted_password);
        credential.setKey(key);
        return credentialsMapper.updateCredential(credential);
    }
}
