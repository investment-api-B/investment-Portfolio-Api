package org.bartoszwojcik.investmentportfolioapi.service.notification;

import lombok.RequiredArgsConstructor;
import org.bartoszwojcik.investmentportfolioapi.telegram.NotificationBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationBot notificationBot;
    @Value("${telegram.admin.chat.id}")
    private String adminChatId;

    public String userWannaWithdrawal(String userEmail, BigDecimal amount) {
        String info = "User with email %s wanna back his money. amount: %s"
                .formatted(userEmail, amount);
        notificationBot.sendMessage(adminChatId, info);
        return "message sent to admin";
    }
}
