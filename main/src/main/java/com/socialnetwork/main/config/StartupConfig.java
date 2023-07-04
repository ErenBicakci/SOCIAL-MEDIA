package com.socialnetwork.main.config;


import com.socialnetwork.main.entity.Image;
import com.socialnetwork.main.repository.ImageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class StartupConfig implements CommandLineRunner {

    private final ImageRepository imageRepository;

    public StartupConfig(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void run(String... args) {

        Image image = imageRepository.findById("1").orElse(null);
        if (image == null){

            String image64Format = "/9j/4AAQSkZJRgABAQACWAJYAAD/2wCEAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDIBCQkJDAsMGA0NGDIhHCEyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMv/CABEIAMgAyAMBIgACEQEDEQH/xAAwAAEBAAMBAQEAAAAAAAAAAAAABQIDBAEGBwEBAQEBAAAAAAAAAAAAAAAAAAECA//aAAwDAQACEAMQAAAA/XxrmAAAAAAAAAAAAAAAAAdnVNSVnAkuzjsBAAAAAAAAMq2PVnYKA5+gQca0m4CwAAAAABu090tITYAACLam2cIuAAAAAAHfwdUtYTYAACdRkpyjWAAAAAAHvhbmyPXzr0KAPDCJv57gLAAAAAAAG7SWx0fP5y3UTArTdJAsAAAAAAAMu6WfurbGpeygODygiXptK+fXOJOBljcgAAAAOn2rNY5k0AAAABhLr+JAdXLrIIAA2a6UvZkTYAAAAAAGMW5w2TRcAALMa5NbBNAAAAAAANO7WQxrAIAuQ7k3sEoAAAAAADXs1kMawCALhN7BKAAAAAAA1hDGsAn/xAA3EAACAQEDCAkCBQUAAAAAAAABAgMEABExBRIgITBAQVETIjI0YXFyobFTkRQjYoGCEBVSYNH/2gAIAQEAAT8A/wBUAJNwBJ5C0WT3cXyHMHLE2SggXFS3mbfhIPpLZ8nwNgCp8DaXJ8iAmM545YGxBBuIuPLco42kcIovJtT0qQLfi/FtKopUnXk/BrSI0blHFxG40VP0MWcw67e2wrafpYs5R111+Y3Clj6WoVTgNZ2VVH0VQwGB1jb5NX8yRuQA2WUl/MjbmCNvkw65B5bLKR1xjwO3oHzKi44MLtlXvn1N3+Iu26sVYMMQbxaGQSxK44+2wmlEMTOeGHjYksxY4k3ncKSpMD3G8ocfDxsrB1DKQQcCNJmCqWY3AYm1XUmd7hqQYblBUyQHVrXiptFWRS6s7Nbk39cLS1kMV4zs5uS2nqZJzrNy8FG6rLInZdh5G34qf6rfezSSP2nY+Z3TE3WSlnfCMgczqsuTpD2nUe9hk3nL9lt/bV+q32scmDhL91s2TpB2XU+1npZ0xjJHMa7YG47dEeRs1FLHwtDk7jK38RaOGOIdRAP22EkEco66A+N1pcncYm/i3/bOjRtmupB8dpTUjTdZtSc+do4kiXNQADaSRJKua4vFqmkaDrL1k58tlR0vTHPfsD3sAALhtiARccLVlL0Jz07B9thDEZpVQfueQsiBFCqLgBcNwdA6lWF4Oo2miMMrIeGB5jTydEAjSHEm4eW5ZRjvRZBiNR8tOlXNpox+m/cqtc6lkH6b9ODu8fpHxuU/d5PSdODu8fpHxuU/d5PSfjTg7vH6R8blP3eT0n404O7x+kfG5T93k9J+ND//xAAZEQADAQEBAAAAAAAAAAAAAAABETAAIED/2gAIAQIBAT8A8Tz9gqKiSyyk8888+3QxHBqamn//xAAbEQEAAwEAAwAAAAAAAAAAAAABESAwABIxQP/aAAgBAwEBPwD4o6Mwo4FXAq+sRouQ95cuUdB0HR0XC6TUxaGpqaf/2Q==";


            Image image1 = Image
                    .builder()
                    .id("1")
                    .name("default")
                    .contentType("jpg")
                    .data(Base64.getDecoder().decode(image64Format))
                    .build();
            imageRepository.save(image1);
        }
    }
}
