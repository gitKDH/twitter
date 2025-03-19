package com.hyun.twitter.follow.mapper;

import com.hyun.twitter.follow.entity.Follow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper {
    int followUser(Follow follow);
}
