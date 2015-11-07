package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("advManager")
public class AdvManagerImpl implements AdvManager {

    @Autowired
    private AdvRepository advRepository;
    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<Advertisement> list() {
        return advRepository.findAll();
    }

    @Override
    public List<Advertisement> list(String pattern) {
        return advRepository.findByShortDescLike(pattern);
    }

    @Override
    public void add(Advertisement adv) {
        advRepository.save(adv);
    }

    @Override
    public void delete(long id) {
        advRepository.delete(id);
    }

    @Override
    public byte[] getPhoto(long id) {
        Photo photo = photoRepository.findOne(id);
        return photo.getBody();
    }
}
