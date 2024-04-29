package com.campus.projectboard.service;

import com.campus.projectboard.domain.UserAccount;
import com.campus.projectboard.dto.UserAccountDto;
import com.campus.projectboard.repository.UserAccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

}
