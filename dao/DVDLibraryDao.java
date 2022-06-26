package dao;

import dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    DVD addDVD(String dvdId, DVD dvd) throws DVDLibraryDaoException;

    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    DVD getDVD(String dvdId) throws DVDLibraryDaoException;

    // Rename to findDVDByTitle()
    List<DVD> findDVD(String title) throws DVDLibraryDaoException;

    DVD removeDVD(String dvdId) throws DVDLibraryDaoException;

    DVD editDVDTitle(String dvdId, String title) throws DVDLibraryDaoException;

    DVD editDVDReleaseDate(String dvdId, String title) throws DVDLibraryDaoException;

    DVD editDVDMpaaRating(String dvdId, String title) throws DVDLibraryDaoException;

    DVD editDVDDirectorName(String dvdId, String title) throws DVDLibraryDaoException;

    DVD editDVDStudio(String dvdId, String title) throws DVDLibraryDaoException;

    DVD editDVDUserRating(String dvdId, String title) throws DVDLibraryDaoException;

}