	SELECT		a.name as artName,
					alb.title as albTitle,
					t.name as tName,
					mt.name as mType
    FROM		artist a INNER JOIN album alb
								INNER JOIN track t
								INNER JOIN mediatype mt 
					ON t.mediaTypeId=mt.MediaTypeId
					AND t.albumId=alb. albumId
					AND a.ArtistId=alb.ArtistId
    WHERE		mt.Name <> 'MPEG audio file'
	ORDER BY	a.name ASC,
						tName ASC,
						alb.title ASC