package com.movie.test.user.block.service;

import com.movie.test.user.block.dto.BlockDTO;
import com.movie.test.user.block.entity.BlockEntity;
import com.movie.test.user.block.repository.BlockRepository;
import com.movie.test.user.follow.dto.FollowListSearchDTO;
import com.movie.test.user.userinfo.dto.UserDTO;
import com.movie.test.user.userinfo.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class BlockServiceImpl implements BlockService{

    private final BlockRepository blockRepository;

    @Override
    public BlockDTO registBlock(BlockDTO blockDTO) {

        BlockEntity blockEntity = blockRepository.findByUserIdAndTargetId(blockDTO.getUserId(), blockDTO.getTargetId());

        // 이미 차단한 경우 해당 데이터 리턴
        if(blockEntity != null){
            return BlockDTO.toDTO(blockEntity);
        }

        blockDTO.setBlockId(UUID.randomUUID().toString());
        blockEntity = blockRepository.save(BlockDTO.toEntity(blockDTO));

        return BlockDTO.toDTO(blockEntity);
    }

    @Override
    public void cancleBlock(String blockId) {
        blockRepository.deleteById(blockId);
    }

    @Override
    public Slice<UserDTO> getBlockList(FollowListSearchDTO blockListSearchDTO, Pageable pageable) {
        Slice<UserEntity> blockList = blockRepository.getBlockList(blockListSearchDTO, pageable);
        Slice<UserDTO> blockListDTO = blockList.map(UserDTO::toDTO);
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
