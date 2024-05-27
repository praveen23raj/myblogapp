package com.blogapp1.serviceimpl;

import com.blogapp1.entity.Comment;
import com.blogapp1.entity.Post;
import com.blogapp1.payload.CommentDto;
import com.blogapp1.payload.PostDto;
import com.blogapp1.payload.PostWithCommentDto;
import com.blogapp1.repository.CommentRepository;
import com.blogapp1.repository.PostRepository;
import com.blogapp1.service.CommentService;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;


    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(comment);

        return dto;
    }

    @Override
    public PostWithCommentDto findAllCommentByPostId(long id) {
        Post post = postRepository.findById(id).get();

        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        PostWithCommentDto postWithCommentDto=new PostWithCommentDto();
        postWithCommentDto.setCommentDto(dtos);
        postWithCommentDto.setPost(dto);

        return  postWithCommentDto;
    }


    Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

    CommentDto mapToDto(Comment comment) {
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }
}
