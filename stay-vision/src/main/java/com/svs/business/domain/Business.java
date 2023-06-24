package com.svs.business.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import com.svs.member.domain.Member;
import com.svs.reply.domain.Reply;
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
