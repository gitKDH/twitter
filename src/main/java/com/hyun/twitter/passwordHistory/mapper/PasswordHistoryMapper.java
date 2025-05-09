package com.hyun.twitter.passwordHistory.mapper;

import com.hyun.twitter.passwordHistory.entity.PasswordHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PasswordHistoryMapper {
    int addPasswordHistory(PasswordHistory history);

    List<PasswordHistory> findRecentPasswords(@Param("userId") Long userId);
}
