package com.ipi.senaihub.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

// 1. Serviço para manipular os uploads de arquivos para o disco local.
@Service
public class FileStorageService {

    // 2. Diretório raiz lido do application.properties
    private final Path localDeArmazenamento;

    public FileStorageService(@Value("${api.upload.dir}") String uploadDir) {
        this.localDeArmazenamento = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            // 3. Cria a pasta no disco do servidor caso ela não exista.
            Files.createDirectories(this.localDeArmazenamento);
        } catch (Exception ex) {
            throw new RuntimeException("Não foi possível criar o diretório onde os arquivos serão armazenados.", ex);
        }
    }

    // 4. Salva a imagem enviada pelo usuário e retorna o caminho (URL) final da imagem.
    public String salvarImagem(MultipartFile arquivo) {
        if (arquivo == null || arquivo.isEmpty()) {
            return null; // O arquivo é opcional
        }

        try {
            // 5. Geramos um nome único UUID para evitar que imagens com o mesmo nome se sobrescrevam.
            String nomeUnico = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
            
            // 6. Montamos o caminho absoluto onde o arquivo será jogado.
            Path caminhoDestino = this.localDeArmazenamento.resolve(nomeUnico);
            
            // 7. Copiamos os bytes da memória (MultipartFile) para o disco rígido (Files.copy).
            Files.copy(arquivo.getInputStream(), caminhoDestino, StandardCopyOption.REPLACE_EXISTING);

            // 8. Retornamos uma URL/Path relativo que poderá ser acessado pelo Frontend depois.
            return "/uploads/noticias/" + nomeUnico;

        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível armazenar o arquivo " + arquivo.getOriginalFilename() + ". Tente novamente!", ex);
        }
    }
}