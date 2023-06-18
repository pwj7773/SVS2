package com.svs.business.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.svs.member.domain.Member;
import com.svs.support.BaseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "BUSINESS", schema = "svs")
public class Business extends BaseEntity {
	@Column(name="BUSINESS_NAME")
	String businessName;
	@Column(name="BUSINESS_ADDRESS")
	String businessAddress;
	@Column(name="TOTAL_ROOM")
	int totalRoom;
	@Column(name="PHONE")
	String phone;
	@Column(name="USER_ID")
	String userId;
    @ManyToOne(
            targetEntity = Member.class
            ,fetch = FetchType.LAZY
            ,optional = false
    )
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"
            , insertable = false, updatable = false)
	Member member;
}
