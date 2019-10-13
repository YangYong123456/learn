package edu.hubu.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.hubu.learn.dao.MusicDao;
import edu.hubu.learn.entity.Music;

@Service
public class MusicService {

    @Autowired
    private MusicDao MusicDao;

    public Music getMusic(Long id) {
        return MusicDao.findById(id).get();
    }
    public List<Music> getMusics(){
        return MusicDao.findAll(new Sort(Direction.DESC, "id"));
    }
    public List<Music> searchMusics(String keyword) {
        Music music = new Music();
        music.setName(keyword);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("musicname", match->match.contains());
        Example<Music> example = Example.of(music, matcher);
        Sort sort = new Sort(Direction.DESC, "id");
        return MusicDao.findAll(example, sort);
    }

    public Music addMusic(Music user) {
        return MusicDao.save(user);
    }

    public void deleteMusic(Long id) {
        MusicDao.deleteById(id);
    }

    public void modifyMusic(Music music) {
        MusicDao.save(music);
    }
}