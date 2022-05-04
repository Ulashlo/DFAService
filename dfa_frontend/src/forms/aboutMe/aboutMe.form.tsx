import React from 'react';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { ADMIN } from '@src/utils/constraints';
import { UserAboutMeForm } from '@src/components/aboutMe/userAboutMe';
import { AdminAboutMeForm } from '@src/components/aboutMe/adminAboutMe/adminAboutMeForm.component';

export function AboutMeForm() {
  const authInfo = useAuthInfo();

  return authInfo?.roles.includes(ADMIN) ? <AdminAboutMeForm /> : <UserAboutMeForm />;
}
