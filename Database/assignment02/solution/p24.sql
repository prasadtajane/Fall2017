select	t.name as track_name,
			alb.title as album_title,
			art.name as artist_name,
			g.name as genre_name
from	Track t 
				inner join
			Album alb
				inner join
			Artist art
				inner join
			Genre g
			on	t.albumId = alb.albumId
			and	alb.artistId = art.artistId 
			and	g.genreId = t.genreId
where	t.composer isnull 
and		t.trackId in (
				select	trackId
				from	PlaylistTrack 
				where	playListId in (
							select	playListId 
							from	Playlist
							where	name in (
											'Grunge',
											'90’s Music'))
group by	trackId
having	count(*) > 1)
