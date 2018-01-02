import model.Artist;
import model.DataSource;
import model.SongArtist;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataSource datasource = new DataSource();
        if(!datasource.open())
        {
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtists(DataSource.ORDER_BY_ASC);
        if(artists == null)
        {
            System.out.println("No artists");
            return;
        }

        for(Artist artist: artists)
        {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        List<String> albumsForArtist = datasource.queryAlbumsForArtist("Carole King", DataSource.ORDER_BY_ASC);
        for(String album : albumsForArtist)
            System.out.println(album);

        List<SongArtist> songArtists = datasource.queryArtistsForSong("Go Your Own Way", DataSource.ORDER_BY_ASC);
        if(songArtists == null)
        {
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for(SongArtist artist: songArtists)
        {
            System.out.println("Artist name = " + artist.getArtistName() +
                ", Album name = " + artist.getAlbumName() +
                ", Track = " + artist.getTrack());
        }

        datasource.querySongsMetaData();

        int count = datasource.getCount(DataSource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);

        datasource.createViewForSongArtist();

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter a song title: ");
//        String title = scanner.nextLine();
//
//        songArtists = datasource.querySongInfoView(title);
//        if(songArtists.isEmpty())
//        {
//            System.out.println("Couldnt find artist for song");
//            return;
//        }
//
//        for(SongArtist artist: songArtists)
//        {
//            System.out.println("FROM VIEW - artist name = " + artist.getArtistName() +
//                ", album name = " + artist.getAlbumName() + ", track number = " + artist.getTrack());
//        }

        datasource.insertSong("Bird Dog", "Everly Brothers", "All-Time Greatest Hits", 7);


        datasource.close();
        
    }
}
