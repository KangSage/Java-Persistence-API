package hellojpa;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Entity 클래스에는 public or protected 기본 생성자가 필수<br>
 * - JPA가 리플렉션 API, Proxy 기술 등을 활용하여 객체의 기본 생성자를 사용한다.<br>
 * - final 클래스, enum, interface, inner 클래스는 사용 불가 - 저장할 필드에 final 사용 X<br>
 */
@Entity // JPA가 관리하는 객체 - DB 테이블과 매핑할 클래스에는 필수
@Table
public class Member {
  @Id
  private Long id;

  /**
   * Column 애노테이션에 사용할 수 있는 옵션들
   * insertable, updatable : JPA에서 insert, update를 막을 수 있다. 기본 값은 true
   * nullable: false일 경우 not null 제약 조건. 기본 값은 true
   * unique: 유니크 제약 조건 여부
   *         - constraint Key 이름을 알아보기 힘들게 되므로
   *         - @Table의 uniqueConstraints 옵션을 주로 사용한다.
   * length: String 타입 필드의 길이를 정의
   * columnDefinition: DB 컬럼 정보를 직접 줄 수 있다.
   * precision: 소수점을 포함한 전체 자릿수 BigDecimal, BigInteger 타입에서 사용한다.
   * scale: 소수의 자릿수. double, float에는 적용되지 않고 정말 정밀한 자릿수에만 사용 가능.
   */
  @Column(name = "name") // DB 컬럼명과 필드명이 다를 경우 직접 지정
  private String username;

  private Integer age;

  /**
   * Enumerated 애노테이션은 기본 값인 ordinal은 순서 값이 DB에 들어가는데
   * 그 경우 Enum에 타입이 추가 되거나 순서가 변경되면 숫자가 달라져 굉장히 위험하다.
   */
  // @Enumerated
  @Enumerated(EnumType.STRING) // 필드를 Enum 타입을 사용
  private RoleType roleType;

  /**
   * 날짜 타입의 필드의 경우 사용하는 애노테이션
   * 최신 Hibernate 기능 - Java 8의 최신 LocalDateTime, LocalDate등을 사용하면 필요없다.
   */
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate;

  @Lob // clab, blab 등 varchar를 넘어서는 큰 컨텐츠의 경우
  private String description;

  @Transient // DB 매핑과 상관없는 필드가 필요할 때 사용
  private int temp;

  public Member() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public RoleType getRoleType() {
    return roleType;
  }

  public void setRoleType(RoleType roleType) {
    this.roleType = roleType;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getTemp() {
    return temp;
  }

  public void setTemp(int temp) {
    this.temp = temp;
  }
}
