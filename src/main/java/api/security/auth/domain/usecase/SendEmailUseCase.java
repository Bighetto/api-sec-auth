package api.security.auth.domain.usecase;

public interface SendEmailUseCase {

    void execute(String to, String name, String password);
    
}
