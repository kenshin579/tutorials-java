package kr.pe.advenoh.model.entity.notworking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.pe.advenoh.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "video_collection")
public class VideoCollection extends DateAudit {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "video_collection_seqno")
	private Long videoCollectionNo;

	@Column(name = "collection_title", length = 100)
	private String collectionTitle;

	@JsonIgnore
	@OneToMany(mappedBy = "videoCollection", fetch = FetchType.LAZY)
	private List<VideoCollectionMapping> videoCollectionMappings = new ArrayList<>();
}
