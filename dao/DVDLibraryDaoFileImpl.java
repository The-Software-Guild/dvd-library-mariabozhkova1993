package dao;

import dto.DVD;

import java.io.*;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    public static final String DVD_FILE = "dvds.txt";
    public static final String DELIMITER = ",";
    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDVD(String dvdId, DVD dvd) throws DVDLibraryDaoException {

        loadDVDsFromFile();
        DVD newDVD = dvds.put(dvdId, dvd);
        writeDVDsToFile();
        
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadDVDsFromFile();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String dvdId) throws DVDLibraryDaoException {
        loadDVDsFromFile();
        return dvds.get(dvdId);
    }

    @Override
    public List<DVD> findDVD(String title) {

        List<DVD> filteredDVDs = new ArrayList<>();

        for (DVD dvd : dvds.values()) {
            filteredDVDs.add(dvd);
        }
        return filteredDVDs;
    }

    @Override
    public DVD removeDVD(String dvdId) throws DVDLibraryDaoException {
        loadDVDsFromFile();
        DVD removedDVD = dvds.remove(dvdId);
        writeDVDsToFile();
        return removedDVD;
    }

    @Override
    public DVD editDVDTitle(String dvdId, String title) throws DVDLibraryDaoException {
        DVD newDVD = getDVD(dvdId);
        newDVD.setTitle(title);
        dvds.put(dvdId, newDVD);
        return newDVD;
    }

    @Override
    public DVD editDVDReleaseDate(String dvdId, String releaseDate) throws DVDLibraryDaoException {
        DVD newDVD = getDVD(dvdId);
        newDVD.setTitle(releaseDate);
        dvds.put(dvdId, newDVD);
        return newDVD;
    }

    @Override
    public DVD editDVDMpaaRating(String dvdId, String mpaaRating) throws DVDLibraryDaoException {
        DVD newDVD = getDVD(dvdId);
        newDVD.setTitle(mpaaRating);
        dvds.put(dvdId, newDVD);
        return newDVD;
    }

    @Override
    public DVD editDVDDirectorName(String dvdId, String directorName) throws DVDLibraryDaoException {
        DVD newDVD = getDVD(dvdId);
        newDVD.setTitle(directorName);
        dvds.put(dvdId, newDVD);
        return newDVD;
    }

    @Override
    public DVD editDVDStudio(String dvdId, String studio) throws DVDLibraryDaoException {
        DVD newDVD = getDVD(dvdId);
        newDVD.setTitle(studio);
        dvds.put(dvdId, newDVD);
        return newDVD;
    }

    @Override
    public DVD editDVDUserRating(String dvdId, String userRating) throws DVDLibraryDaoException {

        DVD newDVD = getDVD(dvdId);
        newDVD.setTitle(userRating);
        dvds.put(dvdId, newDVD);
        return newDVD;
    }

    // Object to text
    private String marshallDVD(DVD dvd) {
        String DVDAsText = dvd.getDvdId() + DELIMITER;
        DVDAsText += dvd.getTitle() + DELIMITER;
        DVDAsText += dvd.getReleaseDate() + DELIMITER;
        DVDAsText += dvd.getMpaaRating() + DELIMITER;
        DVDAsText += dvd.getDirectorName() + DELIMITER;
        DVDAsText += dvd.getStudio() + DELIMITER;
        DVDAsText += dvd.getUserRating() + DELIMITER;

        return DVDAsText;
    }

    // Text to object
    private DVD unmarshallDVD(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        String dvdId = dvdTokens[0];
        DVD dvdFromFile = new DVD(dvdId);

        dvdFromFile.setTitle(dvdTokens[1]);
        dvdFromFile.setReleaseDate(dvdTokens[2]);
        dvdFromFile.setMpaaRating(dvdTokens[3]);
        dvdFromFile.setDirectorName(dvdTokens[4]);
        dvdFromFile.setStudio(dvdTokens[5]);
        dvdFromFile.setUserRating(dvdTokens[6]);
        return dvdFromFile;
    }

    // Load from file
    // From lines in file to DVD objects in dvds map
    private void loadDVDsFromFile() throws DVDLibraryDaoException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException("Could not load DVD data into memory", e);
        }

        String currentLine;
        DVD currentDVD;
        while (scanner.hasNext()) {
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvds.put(currentDVD.getDvdId(), currentDVD);
        }
        scanner.close();
    }

    // Write to file
    //
    private void writeDVDsToFile() throws DVDLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));

        } catch (IOException e) {
            throw new DVDLibraryDaoException("Could not write DVD data to file", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();

        for (DVD dvd : dvdList) {

            dvdAsText = marshallDVD(dvd);
            out.println(dvdAsText);
            out.flush();
        }

        out.close();
    }

}