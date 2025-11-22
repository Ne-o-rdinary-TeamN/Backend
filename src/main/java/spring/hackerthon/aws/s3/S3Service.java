package spring.hackerthon.aws.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.hackerthon.global.common.Uuid;
import spring.hackerthon.global.common.repository.UuidRepository;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3Manager amazonS3Manager;
    private final UuidRepository uuidRepository;

    public String upload(MultipartFile image) throws IOException {
        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        return amazonS3Manager.uploadFile(amazonS3Manager.generateKeyName(savedUuid), image);
    }
}
