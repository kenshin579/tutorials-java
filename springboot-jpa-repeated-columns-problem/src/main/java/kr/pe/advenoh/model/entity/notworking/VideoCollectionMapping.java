//package kr.pe.advenoh.model.entity;
//
//import kr.pe.advenoh.model.audit.DateAudit;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Getter
//@Setter
//@ToString
//@Entity
//@Table(name = "video_collection_mapping")
//public class VideoCollectionMapping extends DateAudit {
//	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "video_collection_mapping_seqno")
//	private Long videoCollectionMappingNo;
//
//	@Column(name = "video_collection_seqno")
//	private Long videoCollectionNo;
//
//	@Column(name = "video_seqno")
//	private Long videoNo;
//
//	protected Integer orderNo;
//
//	@ManyToOne
//	@JoinColumn(name = "video_collection_seqno")
//	private VideoCollection videoCollection;
//
//	@ManyToOne
//	@JoinColumn(name = "video_seqno")
//	private Video video;
//}
