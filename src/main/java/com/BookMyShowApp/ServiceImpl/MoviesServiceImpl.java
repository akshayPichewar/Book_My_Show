package com.BookMyShowApp.ServiceImpl;

import com.BookMyShowApp.dto.MovieDto;
import com.BookMyShowApp.entity.Movies;
import com.BookMyShowApp.exception.ResourceNotFound;
import com.BookMyShowApp.repository.MoviesRepository;
import com.BookMyShowApp.service.MoviesService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MoviesRepository moviesRepository;

    @Transactional
    @Override
    public MovieDto addMovies(MovieDto movieDto)
    {
        Movies movies=modelMapper.map(movieDto,Movies.class);

        Movies saved = moviesRepository.save(movies);
       return modelMapper.map(movies,MovieDto.class);
    }

    @Override
    public List<MovieDto> getAllMovies() {

        List<Movies> all = moviesRepository.findAll();

        List<MovieDto> collect = all.stream().map(movies -> modelMapper.map(movies,MovieDto.class)).collect(Collectors.toList());
        return collect;
    }
    @Override
    public void deleteMovies(Integer id) {
        Movies movies = moviesRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Movies id not found"));
        System.out.println("deleted movie name: "+movies.getMovieName());
       moviesRepository.deleteById(movies.getId());
    }
    @Override
    public MovieDto FindMoviesById(Integer id) {
        Optional<Movies> byId = moviesRepository.findById(id);

        if(byId.isPresent()){
            Movies movies = byId.get();

            return modelMapper.map(movies,MovieDto.class);
        }
        else {
            throw new ResourceNotFound("Movies id is not Found "+id);
        }
    }
    // Fetch the movies by using any letter
    @Override
    public List<MovieDto> findMoviesByUsingAnyLetter(String name) {
        List<Movies> byMoviesNameContaining = moviesRepository.findByMovieNameContaining(name);
        List<MovieDto> moviesList = byMoviesNameContaining.stream().map(movies -> modelMapper.map(movies,MovieDto.class)).collect(Collectors.toList());
        if(moviesList.isEmpty()){
            throw  new ResourceNotFound("Not Movies found by this letter");
        }
        return moviesList;
    }




}
