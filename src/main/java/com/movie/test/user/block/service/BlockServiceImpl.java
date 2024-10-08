package com.movie.test.user.block.service;

import com.movie.test.common.cef.CustomUUID;
import com.movie.test.user.block.dto.BlockDto;
import com.movie.test.user.block.dto.BlockSaveDto;
import com.movie.test.user.block.entity.BlockEntity;
import com.movie.test.user.block.repository.BlockRepository;
import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.movie.test.user.userinfo.dto.UserDto;
import com.movie.test.user.userinfo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BlockServiceImpl implements BlockService{

    private final BlockRepository blockRepository;

    @Override
    public BlockDto saveBlock(BlockSaveDto blockSaveDto) {

        BlockEntity blockEntity = blockRepository.findByUserIdAndTargetId(blockSaveDto.getUserId(), blockSaveDto.getTargetId());

        // 이미 차단한 경우 해당 데이터 리턴
        if(blockEntity != null){
            return BlockDto.toDTO(blockEntity);
        }

        blockSaveDto.setBlockId(CustomUUID.createUUID());
        blockEntity = blockRepository.save(BlockSaveDto.toEntity(blockSaveDto));

        return BlockDto.toDTO(blockEntity);
    }

    @Override
    public void cancleBlock(String blockId) {
        blockRepository.deleteById(blockId);
    }

    @Override
    public Slice<UserDto> getBlockList(FollowListSearchDTO blockListSearchDTO, Pageable pageable) {
        Slice<UserEntity> blockList = blockRepository.getBlockList(blockListSearchDTO, pageable);
        Slice<UserDto> blockListDTO = blockList.map(UserDto::toDTO);
        return blockListDTO;
    }

    @Override
    public boolean isBlocking(String userId, String targetId) {
        return blockRepository.existsByUserIdAndTargetId(userId, targetId);
    }

    @Override
    public Long countBlock(String userId) {
        return blockRepository.countByUserId(userId);
    }
}
