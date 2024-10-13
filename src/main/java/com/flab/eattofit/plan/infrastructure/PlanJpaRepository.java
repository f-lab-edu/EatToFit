package com.flab.eattofit.plan.infrastructure;

import com.flab.eattofit.plan.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanJpaRepository extends JpaRepository<Plan, Long> {

    Plan save(Plan plan);
}
